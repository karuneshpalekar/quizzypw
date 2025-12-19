package com.karunesh.quizzypw.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karunesh.quizzypw.R
import com.karunesh.quizzypw.data.model.OverallAccuracy
import com.karunesh.quizzypw.data.model.PerformanceByTopic
import com.karunesh.quizzypw.data.model.QuizStreak
import com.karunesh.quizzypw.data.model.Student
import com.karunesh.quizzypw.data.model.TodaySummary
import com.karunesh.quizzypw.data.model.WeeklyOverview
import com.karunesh.quizzypw.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())

        }
        viewModel.loadHomeData()
        observeData()
    }

    private fun observeData() {
        viewModel.homeData.observe(viewLifecycleOwner) { response ->

            val student = response.student
            val today = response.todaySummary

            binding.tvHello.text = "Hello ${student.name}!"
            binding.tvClass.text = student.`class`

            bindStats(student)
            bindTodaySummary(today)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindStats(student: Student) {
        binding.availability.text = student.availability.status
        binding.quiz.text = "${student.quiz.attempts} Attempt"
        binding.accuracy.text = student.accuracy.current
    }

    private fun bindTodaySummary(today: TodaySummary) {
        binding.tvFocused.text = today.mood
        binding.tvSummaryQuote.text = "“${today.description}”"
        binding.btnWatch.text = today.recommendedVideo.actionText
    }

    private fun bindWeeklyOverview(weekly: WeeklyOverview) {
        bindAccuracy(weekly.overallAccuracy)
        bindPerformance(weekly.performanceByTopic)
    }

    private fun bindAccuracy(accuracy: OverallAccuracy) {
        binding.tvAccuracyValue.text = accuracy.label
        binding.progressAccuracy.progress = accuracy.percentage
    }

    private fun bindQuizStreak(streak: List<QuizStreak>) {
        val container = binding.llStreakDots

        streak.forEachIndexed { index, item ->
            val imageView = container.getChildAt(index) as ImageView

            val drawable = when (item.status) {
                "done" -> R.drawable.streak_done
                else -> imageView.drawable
            }

        }
    }

    private fun bindPerformance(topics: List<PerformanceByTopic>) {
        val topicViews = listOf(
            Triple(binding.tvTopic1, binding.ivTrend1, topics.getOrNull(0)),
            Triple(binding.tvTopic2, binding.ivTrend2, topics.getOrNull(1)),
            Triple(binding.tvTopic3, binding.ivTrend3, topics.getOrNull(2))
        )

        topicViews.forEach { (textView, imageView, data) ->
            data?.let {
                textView.text = it.topic
                imageView.setImageResource(
                    if (it.trend == "up") R.drawable.ic_trend_up
                    else R.drawable.ic_trend_down
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}