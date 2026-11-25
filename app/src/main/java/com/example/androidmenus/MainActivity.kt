
package com.example.androidmenus

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ContextMenu
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        title = "Android Menus"

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // Register the TextView for the Context Menu
        val contextText = findViewById<TextView>(R.id.contextText)
        registerForContextMenu(contextText)

        // Configure the Popup Menu button
        val popupButton = findViewById<Button>(R.id.popupButton)

        popupButton.setOnClickListener { button ->

            val popupMenu = PopupMenu(this, button)

            popupMenu.menu.add("Edit")
            popupMenu.menu.add("Share")
            popupMenu.menu.add("Delete")

            popupMenu.setOnMenuItemClickListener { item ->

                showMessage("${item.title} selected")
                true
            }

            popupMenu.show()
        }
    }

    // OPTIONS MENU

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.menu_home -> {
                showMessage("Home selected")
                true
            }

            R.id.menu_settings -> {
                showMessage("Settings selected")
                true
            }

            R.id.menu_about -> {
                showMessage("Android Menus Practical")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // CONTEXT MENU

    override fun onCreateContextMenu(
        menu: ContextMenu,
        view: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, view, menuInfo)

        menu.setHeaderTitle("Message options")

        menu.add(0, 1, 0, "Copy")
        menu.add(0, 2, 1, "Share")
        menu.add(0, 3, 2, "Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            1 -> {
                showMessage("Copy selected")
                true
            }

            2 -> {
                showMessage("Share selected")
                true
            }

            3 -> {
                showMessage("Delete selected")
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    // Display feedback for menu selections

    private fun showMessage(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
