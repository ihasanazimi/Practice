package ir.ha.practice.ui.tabs.samples_tab.retrofit

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/** how to create json object programing.. */

class CreateJsonObject {
    init {
        try {
            // create obj of package : [org.json] package
            val jsonObject = JSONObject().apply { put("key" , "value") }
            val nameJsonArray = JSONArray().apply {
                put("Hasan")
                put("Hosein")
                put("Ehsan")
                put("Rahman")
                put("Aziz")
            }
            // merge to object
            jsonObject.put("name" , nameJsonArray)
        }catch (e : JSONException){ e.printStackTrace() }
    }
}