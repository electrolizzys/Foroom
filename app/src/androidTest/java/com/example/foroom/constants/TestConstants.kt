package com.example.foroom.constants

object Timeouts {
    const val DEFAULT_MS = 10_000L
    const val HOME_SCREEN_MS = 20_000L
    const val IMAGE_LOAD_MS = 15_000L
    const val POLL_INTERVAL_MS = 100L
}

/** Local Foroom Training accounts. They are fictional and exist only on the test device. */
object Accounts {
    const val USER_A_NAME = "lizi_test"
    const val USER_A_PASSWORD = "Lizi123!"
    const val USER_A_NEW_PASSWORD = "Lizi456!"

    const val USER_B_NAME = "lizi_friend"
    const val USER_B_PASSWORD = "Friend123!"

    /** Seeded by the training backend on every device. */
    const val DEMO_USER_NAME = "student"

    const val WRONG_PASSWORD = "WrongPass1!"
    const val NEW_USER_PASSWORD = "Test1234!"
    const val NEW_USER_PREFIX = "valid_"
    const val MISSING_USER_PREFIX = "non_"

    const val AVATAR_INDEX = 1

    /** Avatar ID used when the test setup registers a missing account. */
    const val SETUP_AVATAR_ID = 1
}

object ChatData {
    const val STUDENT_FULL_NAME = "Lizi Kutateladze"

    const val JOHN_WEEK_TITLE = "johnWeek"
    const val FULL_NAME_CHAT_TITLE = "$STUDENT_FULL_NAME conversation"
    const val SHARED_CHAT_TITLE = "something"

    /** Index of the emoji picked when a chat is created; index 0 is preselected by the app. */
    const val CHAT_IMAGE_INDEX = 1
    const val SETUP_EMOJI_ID = 1
}

object MessageData {
    const val DRINK_INVITATION = "let's go for a drink"
    const val MODULE_QUESTION = "Which module do you like most in the Automation Academy?"
    const val GREETING = "Hello from Lizi"
    const val ADDITIONAL_MESSAGE = "Filler message"
    const val REPLY = "Hi Lizi, nice to hear from you"

    /** Enough newer messages to push the greeting out of the first loaded page. */
    const val ADDITIONAL_MESSAGE_COUNT = 25
}

object ProfileLabels {
    const val CHANGE_LANGUAGE_GEORGIAN = "ენის შეცვლა"
    const val SIGN_OUT_GEORGIAN = "გამოსვლა"
    const val CHANGE_LANGUAGE_ENGLISH = "Change Language"
    const val SIGN_OUT_ENGLISH = "Sign Out"
}

object SwipeSettings {
    /** Upper bound so a missing message fails the test instead of swiping forever. */
    const val MAX_SWIPES = 20

    /** A slow drag keeps the fling short, so a message cannot jump past the visible area. */
    const val DURATION_MS = 1500

    /** Fraction of the visible message area kept free at each end of the swipe. */
    const val EDGE_MARGIN_RATIO = 0.2f
}
