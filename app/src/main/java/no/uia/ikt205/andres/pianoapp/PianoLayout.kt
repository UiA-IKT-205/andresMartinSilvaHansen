package no.uia.ikt205.andres.pianoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import no.uia.ikt205.andres.pianoapp.databinding.FragmentPianoBinding

class PianoLayout : Fragment() {

    private var _binding:FragmentPianoBinding? = null
    private val binding get() = _binding!!

    private val fullTones = listOf("C","D","E","F","G","A","B","C2","D2","E2","F2","G2","A2","B2")
    private val semiTones = listOf("C#","D#","F#","G#","A#","C#2","D#2","F#2","G#2","A#2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPianoBinding.inflate(layoutInflater)
        val view = binding.root

        val fm = childFragmentManager
        val ft = fm.beginTransaction()

        fullTones.forEach {
            val fullTonePianoKey = FullTonePianoKeyFragment.newInstance(it)

            fullTonePianoKey.onKeyDown = {
                println("piano key down $it")
            }

            fullTonePianoKey.onKeyUp = {
                println("piano key up $it")
            }

            ft.add(view.pianoKeys.id.fullTonePianoKey, "note_$it")
        }

        semiTones.forEach {
            val halfTonePianoKey = HalfTonePianoKeyFragment.newInstance(it)

            halfTonePianoKey.onKeyDown = {
                println("piano key down $it")
            }

            halfTonePianoKey.onKeyUp = {
                println("piano key down $it")
            }
            ft.add(view.pianoKeys.id.halfTonePianoKey, "note_$it")
        }

        ft.commit()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_piano, container, false)
    }

}