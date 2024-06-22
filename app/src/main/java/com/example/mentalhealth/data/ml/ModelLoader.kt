package com.example.mentalhealth.data.ml

import android.content.Context
import java.nio.MappedByteBuffer

interface ModelLoader {
    fun loadModel(context: Context): MappedByteBuffer
}
