package com.anubhav87.mvvmtutorial.utils

import android.Manifest

/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: This object will holds all the constants required for application.
 */

object Constants {

    var webBasedUrl = ""

    object Prefs {
        const val AUTH_TOKEN = "auth_token"
    }

    object Tag {
        const val LIST = 1
        const val PAGINATION = 2
        const val REFRESH = 3
        const val FLASHBAR = 4
        const val DIALOG = 5
        const val FULL_SCREEN = 6
        const val ACTION_FLASHBAR_DISMISS = 7
        const val ACTION_FLASHBAR_FIXED = 8
        const val NONE = 9

        const val EMPTY_PITCH_DASHBOARD = 10

        const val PLAY = "play"
        const val PITCH_DOWNLOAD = "pitch_download"
        const val PITCH_UPLOAD = "pitch_upload"
        const val PITCH_DELETE = "pitch_delete"

        const val SAVE_PITCHER = "save_pitcher"
        const val GET_PITCHER = "get_pitcher"
    }

    object BundleKeys {
        const val DEVICE_ID = "device_id"
        const val PITCH_DOWNLOAD_URI = "pitch_download_uri"
        const val PITCH_UPLOAD_PATH = "pitch_upload_path"
        const val PITCH = "pitch"
        const val MEDIA_TYPE_ERROR = "invalid_media"
        const val LOCAL_PITCH_UPLOAD_ID = "local_pitch_upload_id"
        const val HANDEDNESS = "handedness"
    }

    object PitchEntity {
        const val PITCH_ID = "pitch_id"
        const val PITCH_THUMB = "pitch_thumb"
        const val PITCH_VIDEO_URL = "pitch_video_url"
    }

    object Handedness {
        const val RIGHT = "R"
        const val LEFT = "L"
    }

    object PitchStates {
        const val NA = -1
        const val UPLOADING = 0
        const val ANALYZING = 1
        const val ANALYSIS_SUCCESS = 2
        const val ANALYSIS_FAIL = 3
    }

    object ScreensTypes {
        const val NONE = -1
        const val SPLASH = 0
        const val GALLERY = 1
        const val PITCH_REPORT = 2
        const val RECORDING = 3
        const val PITCHER_INFORMATION = 4
        const val VIDEO = 5
        const val ANALYZE = 6
        const val PITCH_TRIM = 7
    }

    object Defaults {
        const val MESSAGE_DIALOG_ANIM =
            "https://assets3.lottiefiles.com/datafiles/8UjWgBkqvEF5jNoFcXV4sdJ6PXpS6DwF7cK4tzpi/Check Mark Success/Check Mark Success Data.json"
        const val PROGRESS_ANIM =
            "https://assets1.lottiefiles.com/datafiles/qm9uaAEoe13l3eQ/data.json"
        const val MESSAGE_DIALOG_ERROR_ANIM =
            "https://assets1.lottiefiles.com/datafiles/iXCWwc3IiFNWPPs/data.json"

        const val RECORDER_VIDEO_BITRATE = 15000000
    }

    object NotificationConstants {
        const val NOTIFICATION_CHANNEL_NAME = "PitchAI Notifications"
        const val NOTIFICATION_CHANNEL_DESCRIPTION =
            "Show notification whenever a pitch recording starts downloading"
        const val CHANNEL_ID = "PitchAI_Notification"
        const val NOTIFICATION_TITLE = "Pitch"
        const val NOTIFICATION_ID = 1
    }

    object PermissionConstants {
        val VIDEO_PERMISSIONS =
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
    }

    object Folders {
        const val PITCH_AI_FOLDER = "PitchAi"
        const val VIDEO_FOLDER = "Videos"
        const val LOGS_FOLDER = "Logs"
    }

    object Validations {
        const val NAME = 0
        const val HEIGHT_IN_FEET = 1
        const val HEIGHT_IN_INCHES = 2
        const val WEIGHT = 3
        const val AGE = 4
    }

    object BottomSheetActions {
        const val DOWNLOAD_VIDEO = 0
        const val EXPORT_CSV = 1
    }

    object CSVDownloads {
        const val ANGLES = 0
        const val VELOCITY = 1
    }
}