package com.example.task

import android.graphics.Bitmap




 class ProfileData {
     var imageUrl: String? = null
     var id =0
     var name: String? = null
     var desc: String?= null


     constructor() {}
     constructor(id: Int, name: String?, imageUrl: String?,desc:String?) {
         this.id = id
         this.name = name
         this.imageUrl = imageUrl
         this.desc = desc

     }

     constructor(name: String?, imageUrl: String, desc:String?) {
         this.name = name
         this.imageUrl = imageUrl
         this.desc = desc
     }
 }