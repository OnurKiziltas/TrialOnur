package com.example.trialonur.data.remote

import android.content.Context
import com.example.trialonur.data.DataState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.reflect.Type

open class BaseRemoteDataSource() {
    protected suspend fun <T> getResult(context : Context,fileName : String,type : Type): Flow<DataState<T>> {
        return flow<DataState<T>> {

            val jsonString = context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }

            val data : T = Gson().fromJson(jsonString, type)
            emit(DataState.Success(data))

        }
            .catch {
                emit(DataState.Error(APIError(-1, it.message ?: it.toString())))
            }
            .onStart { emit(DataState.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}

