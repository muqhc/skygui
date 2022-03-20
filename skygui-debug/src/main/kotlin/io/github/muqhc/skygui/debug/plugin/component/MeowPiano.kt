package io.github.muqhc.skygui.debug.plugin.component

import com.destroystokyo.paper.ParticleBuilder
import io.github.muqhc.skygui.SkyDisplay
import io.github.muqhc.skygui.component.SkyComponent
import io.github.muqhc.skygui.component.SquareRenderComponent
import io.github.muqhc.skygui.event.SkyDisplayInteractEvent
import io.github.muqhc.skygui.util.Point
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MeowPiano(center: Point): SkyComponent {
    override val point1: Point = Point(center.x+2.5,center.y+2.5)
    override val point2: Point = Point(center.x-2.5,center.y-2.5)

    val notes = MeowNote.NoteSet.values().mapIndexed { index, noteSet ->
        val angle = 2 * PI * index / MeowNote.NoteSet.values().count() + (PI/2)
        MeowNote(Point(2 * cos(angle), 2 * sin(angle)),noteSet)
    }

    override fun render(display: SkyDisplay) {
        notes.forEach { it.render(display) }
    }

    override fun onClicked(event: SkyDisplayInteractEvent) {
        notes.forEach { if (it.isPointIn(event.traceResult.hitLocationOnDisplay)) it.onClicked(event) }
    }

    class MeowNote(center: Point, val noteSet: NoteSet): SquareRenderComponent {
        override val point1: Point = Point(center.x+0.47,center.y+0.47)
        override val point2: Point = Point(center.x-0.47,center.y-0.47)

        override val renderMaterial: Particle = Particle.DUST_COLOR_TRANSITION

        val clickEffectParticle = Particle.BLOCK_CRACK

        override fun getParticleData(): Any? = Particle.DustTransition(noteSet.color,noteSet.color,1f)

        override fun onClicked(event: SkyDisplayInteractEvent) {
            event.player.playSound(event.player,Sound.ENTITY_CAT_AMBIENT,200f,noteSet.pitch)
            event.player.spawnParticle(
                clickEffectParticle,
                event.traceResult.hitLocation,
                6,0.1,0.1,0.1,0.7,
                noteSet.clickBlockMaterial.createBlockData()
            )
        }

        enum class NoteSet(val color: Color, val clickBlockMaterial: Material, val pitch: Float) {
            C4(Color.RED, Material.RED_WOOL, 0.70f),
            D4(Color.ORANGE, Material.ORANGE_WOOL, 0.83f),
            E4(Color.YELLOW, Material.YELLOW_WOOL, 0.96f),
            F4(Color.LIME, Material.LIME_WOOL, 1.09f),
            G4(Color.AQUA, Material.LIGHT_BLUE_WOOL, 1.22f),
            A4(Color.BLUE, Material.BLUE_WOOL, 1.35f),
            B4(Color.PURPLE, Material.PURPLE_WOOL, 1.48f)
        }
    }
}