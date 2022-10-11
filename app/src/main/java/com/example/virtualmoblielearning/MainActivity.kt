package com.example.virtualmoblielearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.virtualmoblielearning.databinding.ActivityNaviBinding


private const val TAG_HOME = "home_fragment"
private const val TAG_STUDY = "study_fragment"
private const val TAG_MY_PAGE = "my_fragment"
private const val TAG_COMMUNITY = "community_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {

                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.studyFragment -> setFragment(TAG_STUDY, StudyFragment())
                R.id.communityFragment -> setFragment(TAG_COMMUNITY, CommunityFragment())
                R.id.myPageFragment-> setFragment(TAG_MY_PAGE, MyFragment())


            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val study = manager.findFragmentByTag(TAG_STUDY)
        val home = manager.findFragmentByTag(TAG_HOME)
        val my = manager.findFragmentByTag(TAG_MY_PAGE)
        val community = manager.findFragmentByTag(TAG_COMMUNITY)

        if (study != null){
            fragTransaction.hide(study)
        }

        if (home != null){
            fragTransaction.hide(home)
        }

        if (my != null) {
            fragTransaction.hide(my)
        }

        if (community != null) {
            fragTransaction.hide(community)
        }

        if (tag == TAG_STUDY) {
            if (study!=null){
                fragTransaction.show(study)
            }
        }
        else if (tag == TAG_HOME) {
            if (home != null) {
                fragTransaction.show(home)
            }
        }

        else if (tag == TAG_MY_PAGE){
            if (my != null){
                fragTransaction.show(my)
            }
        }

        else if (tag == TAG_COMMUNITY){
            if (community != null){
                fragTransaction.show(community)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}