package com.example.mvp2.presenter

interface MainPresenter {
    fun getAllImages(page: Int = 1, perPage: Int = 50)
}