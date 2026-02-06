# Add project specific ProGuard rules here.

# Keep Room annotations
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }

# Keep data classes
-keep class com.example.caveavin.data.model.** { *; }
