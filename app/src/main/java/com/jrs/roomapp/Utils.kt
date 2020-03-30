package com.jrs.roomapp

import android.app.AlertDialog
import android.content.Context

class Utils {
    companion object {
        fun dialog(
            context: Context,
            titleText: String,
            messageText: String,
            positiveText: String,
            negativeText: String,
            positive: () -> Unit,
            negative: () -> Unit
        ) {
            val builder = AlertDialog.Builder(context)

            // Set the alert dialog title
            builder.setTitle(titleText)

            // Display a message on alert dialog
            builder.setMessage(messageText)

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton(positiveText) { dialog, which -> positive() }


            // Display a negative button on alert dialog
            builder.setNegativeButton(negativeText) { dialog, which -> negative() }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
    }
}