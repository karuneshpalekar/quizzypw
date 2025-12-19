package com.karunesh.quizzypw.data.model

data class WeeklyOverview(
    val quizStreak: List<QuizStreak>,
    val overallAccuracy: OverallAccuracy,
    val performanceByTopic: List<PerformanceByTopic>
)
