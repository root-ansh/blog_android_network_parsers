package input_data

object MyInputs {
    //999888777666555444
    val i1 = """
       {
         "requested_at": "2020-04-03T09:44:57",
         "planets": [
           {
             "name": "venus",
             "found_by": "jagga singh",
             "population": null,
             "moons": []
           },
           {
             "name": "earth",
             "population": 999999999999999999,
             "distnace_from_sun": 999888777666555444333222111.32,
             "moons": [
               {
                 "name": "moon",
                 "found_by": "neil armstrong"
               }
             ]
           },
           {
             "name": "mars",
             "found_by": "elon musk",
             "population": 1,
             "distnace_from_sun": 999888777666555444333222111000.32,
             "moons": [
               {"name": "tyler"},
               {
                 "name": "jackson",
                 "found_by": "jackson hanama"
               }
             ]
           }
         ]
       }
    """.trimIndent()

}