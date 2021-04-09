package ru.android.simpleanalitycs.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import ru.android.simpleanalitycs.R
import ru.android.simpleanalitycs.databinding.FragmentHomeBinding
import ru.android.simpleanalitycs.service.AnalyticalService.trackEvent
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        return binding.root
    }

    //The first one is Logcat, which is the log log we want to get.
    //The second is - s, which means filtering.
    //The third is that the type W we want to filter represents warm. We can also replace it with D: debug, I: info, E: error, and so on.
    val running = arrayOf("logcat", "-s", "adb logcat *: I")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            trackEvent(turninOn = true, "Button Click \"Event\"")

            try {
                val process = Runtime.getRuntime().exec(running)
                val bufferedReader = BufferedReader(
                    InputStreamReader(process.inputStream)
                )
                val log = StringBuilder()
                var line: String? = ""
                while (bufferedReader.readLine().also { line = it } != null) {
                    log.append(line)
                }
                textView.text = log.toString()
            } catch (e: IOException) {
                // Handle Exception
            }
        }
    }
}