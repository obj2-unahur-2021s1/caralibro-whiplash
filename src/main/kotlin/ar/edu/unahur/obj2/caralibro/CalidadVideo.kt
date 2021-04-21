package ar.edu.unahur.obj2.caralibro

abstract class CalidadVideo(val segundos: Int){
    abstract fun tamanioVideo(): Int
    //abstract fun obtenerTiempoEnSegundos() : Int
    //fun obtenerTiempoEnSegundos() = this.segundos
}

class videoSd(segundos: Int) : CalidadVideo(segundos) {
    override fun tamanioVideo() = segundos
    //override fun obtenerTiempoEnSegundos() = this.segundos

}

open class videoHd720p(segundos: Int) : CalidadVideo(segundos){
    override fun tamanioVideo()= segundos * 3
}


class videoHd1080p (segundos: Int) : videoHd720p(segundos){
    override fun tamanioVideo()= super.tamanioVideo() * 2
}
