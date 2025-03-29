package com.project.home.data.service
import com.project.home.domain.service.DatabaseService
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement

class FirebaseDatabaseServiceImpl : DatabaseService {

    private val database: DatabaseReference = Firebase.database.reference()

    override fun <T> get(path: String, serializer: KSerializer<T>): Flow<T?> {
        return database.child(path).valueEvents.map { snapshot ->
            snapshot.value?.let { value ->
                try {
                    println("VINCENT-snaphost - $value")
                    val map = value as Map<String, Any>
                    val json = map.mapValues { (_, v) ->
                        when (v) {
                            is String -> JsonPrimitive(v)
                            is Int -> JsonPrimitive(v)
                            is Boolean -> JsonPrimitive(v)
                            is Double -> JsonPrimitive(v)
                            is Float -> JsonPrimitive(v)
                            is Long -> JsonPrimitive(v)
                            is Number -> JsonPrimitive(v)
                            null -> JsonPrimitive(null as String?)
                            else -> JsonPrimitive(v.toString())
                        }
                    }
                    val jsonElement = Json.encodeToJsonElement(json)
                    Json.decodeFromJsonElement(serializer, jsonElement)
                } catch (e: Exception) {
                    println("Error parsing data: ${e.message}")
                    null
                }
            }
        }
    }
}