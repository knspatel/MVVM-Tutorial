package com.anubhav87.mvvmtutorial.utils

import android.os.Environment
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object FileUtils {

    fun getVideoFilePath(): String? {
        return getVideoFilePath(null)
    }

    /**
     * Created by 2503 on 12/13/2019
     *
     * Modified by 2503 on 12/13/2019
     *
     * Purpose : Creates a file in SD card inside the folder named "Pitch Videos" and return the absolute path of the file.
     *
     * @param fileToSaveTo File name
     * @return Created file with the name as if the given fileName is empty[System.currentTimeMillis].
     */
    fun getVideoFilePath(fileToSaveTo: String?): String? {
        //TODO: To be changed for PitchAi folder in storage according to Android 10.

        val filename =
            if (fileToSaveTo.isNullOrEmpty()) {
                val sdf = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS", Locale.US)
                "${sdf.format(Date())}.png"
            } else fileToSaveTo

        val dir =
            File(
                Environment.getExternalStorageDirectory(),
                Constants.Folders.PITCH_AI_FOLDER + File.separator + Constants.Folders.VIDEO_FOLDER
            )

        if (!dir.exists()) {
            dir.mkdirs()
        }
        return "${dir.absolutePath}$filename"
    }

    /**
     * Created by 2503 on 12/24/2019
     *
     * Modified by 2503 on 12/24/2019
     *
     * Purpose : Temp log file path
     *
     */
    private fun getLogFilePath(): String? {
        //TODO: To be changed for PitchAi folder in storage according to Android 10.

        val filename = "pitchAiLog.txt"
        val dir =
            File(
                Environment.getExternalStorageDirectory(),
                Constants.Folders.PITCH_AI_FOLDER + File.separator + Constants.Folders.LOGS_FOLDER
            )
        if (!dir.exists()) {
            dir.mkdirs()
        }

        return "${dir.absolutePath}/$filename"
    }

    /**
     * Created by 2503 on 12/24/2019
     *
     * Modified by 2503 on 12/24/2019
     *
     * Purpose : Append log text in temp log file, if the file doesn't exist then create it and append the log text.
     *
     */
    fun appendLog(text: String?) {
        val logFile = File(getLogFilePath())
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try { //BufferedWriter for performance, true to set append to file flag
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}