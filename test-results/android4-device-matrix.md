# Android 4 — Device matrix

Test class: `com.example.foroom.tests.ConversationTests`
Build: `app` module, `debug` variant (installed as **Foroom Training**)

| # | Device / AVD | Type | Android | API | Screen resolution | Scenario 1 — johnWeek message survives reopening | Scenario 2 — question in own chat | Scenario 3 — two accounts, swipe to older greeting |
|---|---|---|---|---|---|---|---|---|
| 1 | Pixel_7_Pro | Emulator | 13 | 33 | 1440 × 3120 | ✅ Pass | ✅ Pass | ✅ Pass |
| 2 | _not run yet_ | Emulator | 14 | 34 | | | | |
| 3 | _not run yet_ | Emulator | 15 | 35 | | | | |

## How each configuration was checked

On every configuration:

1. Each scenario was run on its own.
2. The full `ConversationTests` class was run.
3. The full class was run again, to confirm that earlier messages and saved sessions do not break the assertions.

## Test data

`PrepareTestDataRule` runs before every test and creates whatever is missing on the device:

- User A `lizi_test` and User B `lizi_friend` (fictional, local-only accounts).
- Chats created by User A: `johnWeek`, `Lizi Kutateladze conversation` and `something`.

Messages sent by the tests end with a unique suffix, so results from earlier runs cannot satisfy a new run's assertions.

Screenshots of the Android Studio test results for each configuration are in `screenshots/android4/`.
