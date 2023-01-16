package kr.texnopos.testtask.data.model

data class GenericResponse(
    val name: String,
    val topLevelDomain: List<String>,
    val alpha2Code: String,
    val alpha3Code: String,
    val callingCodes: List<String>,
    val capital: String,
    val altSpellings: List<String>,
    val subregion: String,
    val region: String,
    val population: Int,
    val latlng: List<Double>,
    val demonym: String,
    val area: Double,
    val timezones: List<String>,
    val borders: List<String>,
    val nativeName: String,
    val numericCode: String,
    val flags: Flags,
    val currencies: List<Currencies>,
    val languages: List<Languages>,
    val translations: Translations,
    val flag: String,
    val regionalBlocs: List<RegionalBlocs>,
    val cioc: String,
    val independent: Boolean
)
