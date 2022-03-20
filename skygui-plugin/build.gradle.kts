repositories {
    mavenLocal()
}

val projectMain = project(":${rootProject.name}-main")

dependencies {
    implementation(projectMain)
}

val pluginName = rootProject.name.split('-').joinToString("") { it.capitalize() }
val packageName = rootProject.name.replace("-", "")
extra.set("pluginName", pluginName)
extra.set("packageName", packageName)

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    create<Jar>("debugJar") {
        archiveBaseName.set(pluginName)

        from(project.sourceSets["main"].output)
        from(projectMain.sourceSets["main"].output)
    }
}
