package sammy.mutahi.gicheru.childSpyApp.data.model


class Photo {

    var nameRandom: String? = null
    var dateTime: String? = null
    var urlPhoto: String? = null

    constructor() {}

    constructor(nameRandom: String?, dateTime: String?, urlPhoto: String?) {
        this.nameRandom = nameRandom
        this.dateTime = dateTime
        this.urlPhoto = urlPhoto
    }

}