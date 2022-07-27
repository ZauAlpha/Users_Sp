package com.example.userssp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding
import com.example.userssp.databinding.ItemUserBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        if (isFirstTime) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(
                    R.string.dialog_confirm
                ) { dialogInterface, i ->
                    val username =
                        dialogView.findViewById<TextInputEditText>(R.id.etUserName).text.toString()
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                    }
                }
                .show()
        }


        userAdapter = UserAdapter(getUsers())
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): List<User> {
        val users = mutableListOf<User>()
        users.add(
            User(
                1,
                "Alain",
                "Nicolas",
                "https://pbs.twimg.com/profile_images/743330187610251264/xkqVE_Qa_400x400.jpg"
            )
        )
        users.add(
            User(
                2,
                "batman",
                "grayson",
                "data:image/jpeg;base64,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"
            )
        )
        users.add(
            User(
                3,
                "Enrique",
                "Cedeyo",
                "https://img.redbull.com/images/c_fill,g_auto,w_540,h_771/q_auto:low,f_auto/redbullcom/2016/07/28/1331809021419_2/enrique-xpeke-cede%C3%B1o-martinez.jpeg"
            )
        )
        return users

    }
}