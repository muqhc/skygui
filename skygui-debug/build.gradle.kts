repositories {
    mavenLocal()
}

val projectMain = project(":${rootProject.name}-main")

dependencies {
    implementation(projectMain)
    implementation("io.github.monun:kommand-api:2.8.1")
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
        archiveBaseName.set(pluginName+"Debug")

        from(project.sourceSets["main"].output)
    }
}
