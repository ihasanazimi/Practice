package ir.ha.dep.ui.fragment.httpSamples

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class CreateJsonObjectDynamically {

    init {
        try {

            // for org.json package
            val jsonObject = JSONObject().apply {
                put("key" , "value")
            }

            val nameJsonArray = JSONArray().apply {
                put("hasan")
                put("ali")
                put("ehsan")
            }


            // merge to object
            jsonObject.put("names" , nameJsonArray)


        }catch (e : JSONException){ e.printStackTrace() }
    }
}