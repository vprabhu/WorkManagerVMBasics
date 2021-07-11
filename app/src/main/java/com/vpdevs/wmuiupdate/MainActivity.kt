package com.vpdevs.wmuiupdate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vpdevs.wmuiupdate.databinding.ActivityMainBinding
import com.vpdevs.wmuiupdate.utils.TOTAL_COUNT
import com.vpdevs.wmuiupdate.viewmodel.SimpleAddViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var viewModel: SimpleAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = viewBinding.root

        setContentView(rootView)

        viewModel = ViewModelProviders.of(this)
            .get(SimpleAddViewModel::class.java)

        viewBinding.buttonAddNumbers.setOnClickListener {
            Log.d("SimpleAddWorker", "buttonAddNumbers")
            viewModel.addNumbers()
        }

        viewModel.workInfos.observe(this, Observer { workInfoList ->
            //  check the workInfoList is null ot empty and
            //  if it empty then simply return
            if (workInfoList.isNullOrEmpty()) {
                return@Observer
            }

            // get the workINfo from workInfoList
            val workInfo = workInfoList[0]

            // now check workInfo's status and update UI
            if (workInfo.state.isFinished) {
                Log.d("SimpleAddWorker", "hide progress bar")
                val totalCount = "Total : ${workInfo.outputData.getInt(TOTAL_COUNT, 0).toString()}"
                viewBinding.textViewResult.text = totalCount
                hideProgressBar()
            } else {
                Log.d("SimpleAddWorker", "show progress bar")
                showProgressBar()
            }
        })
    }

    private fun showProgressBar() {
        viewBinding.buttonAddNumbers.visibility = View.GONE
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        viewBinding.buttonAddNumbers.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.GONE
    }
}