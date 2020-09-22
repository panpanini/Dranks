package jp.co.panpanini.dranks

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesSetKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchService(private val dataStore: DataStore<Preferences>) {

    fun getRecentSearches(): Flow<List<String>> {
        return dataStore.data.map {
            it[KEY_RECENT_SEARCHES]?.toList() ?: listOf()
        }
    }

    suspend fun addRecentSearch(searchTerm: String) {
        dataStore.edit {
            val searches = it[KEY_RECENT_SEARCHES]?.toMutableSet() ?: mutableSetOf()
            searches.add(searchTerm)
            it[KEY_RECENT_SEARCHES] = searches
        }
    }

    companion object {
        private val KEY_RECENT_SEARCHES = preferencesSetKey<String>("RecentSearches")
    }
}