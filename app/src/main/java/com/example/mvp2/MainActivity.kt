package com.example.mvp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp2.adapter.ImageAdapter
import com.example.mvp2.databinding.ActivityMainBinding
import com.example.mvp2.model.Image
import com.example.mvp2.presenter.MainPresenterImpl
import com.example.mvp2.presenter.MainView

class MainActivity : AppCompatActivity(), MainView {
    private val postAdapter by lazy { ImageAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var presenter: MainPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = MainPresenterImpl(this)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
        presenter.getAllImages()
    }

    override fun apiImagesSuccess(images: List<Image>) {
        binding.progressBar.isVisible = false
        postAdapter.submitList(images)
    }

    override fun apiImageFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}