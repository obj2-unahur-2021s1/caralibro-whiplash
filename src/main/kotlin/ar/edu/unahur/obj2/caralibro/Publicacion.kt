package ar.edu.unahur.obj2.caralibro
import kotlin.math.ceil

abstract class Publicacion {
  var cantidadDeMeGustaDeLaPublicacion : Int = 0
  var usuariosQueDieronLikeALaPublicacion = mutableListOf<Usuario>()
  //lateinit var permisoAsignado : Permiso

  abstract fun espacioQueOcupa(): Int

   //falta implementar permisos
  //fun asignarPermiso(permiso: Permiso){
  //  permisoAsignado = permiso
  //}

  fun devolverCantidadDeMeGustaDeLaPublicacion() =
    cantidadDeMeGustaDeLaPublicacion

  fun recibeMeGusta(usuario : Usuario) {

    if (usuariosQueDieronLikeALaPublicacion.contains(usuario)){
      throw Exception ("no se puede dar like dos veces a una misma Publicacion")
    }
    usuariosQueDieronLikeALaPublicacion.add(usuario)
    cantidadDeMeGustaDeLaPublicacion +=1
  }
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  var factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()

  fun cambiarFactorDeCompresion(valorACambiar : Double){
    this.factorDeCompresion = valorACambiar
  }
}

class Texto(val contenido: String) : Publicacion() {

  override fun espacioQueOcupa() = contenido.length
}

class Video(val calidadDeVideo: CalidadVideo) : Publicacion() {

  override fun espacioQueOcupa() = calidadDeVideo.tamanioVideo()

}

