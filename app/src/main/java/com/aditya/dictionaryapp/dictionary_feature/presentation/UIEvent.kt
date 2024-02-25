package com.aditya.dictionaryapp.dictionary_feature.presentation

sealed class UIEvent{
    data class ShowSnackbar(val message: String): UIEvent()
}