package com.anggapambudi.quranapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.anggapambudi.quranapp.R
import com.anggapambudi.quranapp.databinding.ActivityHomeBinding
import com.anggapambudi.quranapp.fragment.BookmarkFragment
import com.anggapambudi.quranapp.fragment.HistoryFragment
import com.anggapambudi.quranapp.fragment.QuranFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //inisialisasi
        val quranFragment = QuranFragment()
        val bookmarkFragment = BookmarkFragment()
        val historyFragment = HistoryFragment()

        moveFragment(quranFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationQuran -> moveFragment(quranFragment)
                R.id.navigationBookmark -> moveFragment(bookmarkFragment)
                R.id.navigationHistory -> moveFragment(historyFragment)
            }
            true
        }


    }

    //set tvFragment
    private fun moveFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.tvFragment, fragment)
            commit()
        }
    }
}