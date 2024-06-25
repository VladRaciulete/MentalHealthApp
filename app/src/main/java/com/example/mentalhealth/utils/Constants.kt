package com.example.mentalhealth.utils

import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.bottomMenu.BottomMenuItem
import com.example.mentalhealth.presentation.navigation.Screen
import com.example.mentalhealth.presentation.profile.SettingsItem

object Constants {
    val bottomMenuElementList = listOf(
        BottomMenuItem(
            "Journal",
            R.drawable.ic_home,
            Screen.JournalScreen.route
        ),
        BottomMenuItem(
            "Recommendations",
            R.drawable.ic_recommended,
            Screen.DailyRecommendationsScreen.route
        ),
        BottomMenuItem(
            "Statistics",
            R.drawable.ic_stats,
            Screen.StatisticsScreen.route
        ),
        BottomMenuItem(
            "Account",
            R.drawable.ic_account,
            Screen.ProfileScreen.route
        )
    )

    const val APP_DATE_FORMAT = "yyyy-MM-dd"

    val studiesList = listOf(
        "Accounting", "Agriculture", "Anthropology", "Architecture", "Art", "Biology",
        "Business Administration", "Chemistry", "Computer Science", "Criminal Justice",
        "Dentistry", "Economics", "Education", "Engineering", "English Literature",
        "Environmental Science", "Finance", "History", "Information Technology", "Journalism",
        "Law", "Linguistics", "Marketing", "Mathematics", "Mechanical Engineering", "Medicine",
        "Music", "Nursing", "Philosophy", "Physics", "Political Science", "Psychology",
        "Sociology", "Statistics", "Theater Arts", "Veterinary Science"
    )

    val occupationsList = listOf(
        "Accountant", "Actor/Actress", "Architect", "Artist", "Athlete",
        "Chef", "Civil Engineer", "Dentist", "Doctor", "Electrician",
        "Fashion Designer", "Firefighter", "Graphic Designer", "Journalist",
        "Lawyer", "Mechanic", "Musician", "Nurse", "Photographer", "Police Officer",
        "Professor", "Software Developer", "Teacher", "Veterinarian", "Waiter/Waitress"
    )

    val genderList = listOf("Male", "Female", "Prefer not to say")

    val maritalStatusList = listOf("Single", "Married", "Divorced", "Prefer not to say")

    val livingAreaList = listOf("Urban", "Rural")

    val publicFigureList = listOf("No", "Yes")

    val profileScreenSettingsList = listOf(
        SettingsItem(R.string.account, Screen.AccountSettingsScreen.route),
        SettingsItem(R.string.notifications, Screen.NotificationsSettingsScreen.route)
    )

    val progressValues = listOf(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

    val todayFeelings = listOf(
        "Ecstatic", "Excited", "Happy", "Joyful", "Content", "Grateful", "Hopeful",
        "Proud", "Relaxed", "Calm", "Fine", "Satisfied", "Valued", "Productive",
        "Motivated", "Loved", "Tired", "Lazy", "Bored", "Annoyed", "Frustrated", "Anxious",
        "Insecure", "Sad", "Sick", "Lonely", "Depressed", "Angry", "Stressed"
    )

    val careOfMyself = listOf(
        "Walked", "Cooked", "Did Yoga", "Read", "Meditated", "Exercised", "Journaled",
        "Listened to music", "Watched a movie", "Spent time with family", "Spent time with friends",
        "Took a bath", "Practiced a hobby", "Gardened", "Danced", "Drew", "Painted", "Knitted", "Baked",
        "Played an instrument", "Cleaned", "Organized", "Studied", "Learned a new skill",
        "Took a nap", "Did a skincare routine", "Went for a run", "Rode a bike", "Played sports",
        "Did a puzzle", "Played a game", "Went to the spa", "Did crafts", "Practiced mindfulness",
        "Practiced gratitude", "Did stretching exercises", "Tried a new recipe",
        "Did breathing exercises", "Had a digital detox", "Volunteered", "Spent time in nature",
        "Practiced positive affirmations", "Planned a trip", "Watched a documentary","Visited a park",
        "Visited a museum", "Had a cup of tea/coffee",
        "Attended a cultural event", "Explored a new place", "Went to the beach",
        "Had a healthy meal", "Hydrated well", "Spent time with pets", "Reflected on goals",
        "Did light physical activity", "Watched a comedy show", "Explored spirituality"
    )

    val dayFeeling = listOf("Very bad", "Bad", "Decent", "Good", "Very good")

    val personalizedRecommendations = listOf(
            "Try to have 7-9 hours of sleep", "Try to maintain a consistent sleep schedule",
            "Don't forget to stay hydrated", "Exercise at least 30 minutes a day", "Keep a balanced diet",
            "Celebrate your achievements", "Share your happiness with others",
            "Engage in something stimulating", "Plan something fun for the day",
            "Do something that makes you smile", "Spend time with loved ones",
            "Spread positivity to others", "Reflect on things that bring you joy",
            "Practice gratitude", "Enjoy the present moment",
            "Write down what you're grateful for", "Share your gratitude with friends and family",
            "Set new goals for the future", "Visualize your dreams",
            "Acknowledge your accomplishments", "Reward yourself",
            "Practice relaxation techniques", "Find a peaceful place to unwind",
            "Check in with yourself regularly", "Engage in hobbies you enjoy",
            "Reflect on your achievements", "Plan for continued success",
            "Surround yourself with supportive people", "Express appreciation to others",
            "Set daily goals", "Create a to-do list for tomorrow",
            "Start a new project", "Seek inspiration from successful individuals",
            "Express your love to those close to you", "Spend quality time with family",
            "Get more sleep", "Take short breaks throughout the day",
            "Start with small tasks", "Set more achievable goals", "Set more challenging goals",
            "Try something new", "Explore a hobby or interest",
            "Practice patience", "Take time to cool off",
            "Identify the source of frustration", "Seek solutions or alternatives",
            "Practice deep breathing exercises", "Focus on the present moment",
            "Challenge negative thoughts", "Practice self-compassion",
            "Reach out to a trusted friend", "Engage in activities that uplift your mood",
            "Rest and take care of your health", "Consult with a healthcare professional",
            "Connect with friends or family", "Join social activities",
            "Seek professional help if needed", "Engage in self-care practices",
            "Channel anger into constructive activities", "Prioritize tasks and manage time effectively"
    )

    val statisticsTimeRangeList = listOf("Last Week", "Last Month", "Last 90 Days")

    val statisticsTimeRangeMap = mapOf(
        "Last Week" to 7,
        "Last Month" to 30,
        "Last 90 Days" to 90
    )
}
