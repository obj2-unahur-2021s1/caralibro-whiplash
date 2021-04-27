package ar.edu.unahur.obj2.caralibro
import kotlin.math.ceil

abstract class Publicacion() {
  lateinit var permiso:String
  lateinit var creadorDeLaPublicacion : Usuario
  var cantidadDeMeGustaDeLaPublicacion : Int = 0
  var usuariosQueDieronLikeALaPublicacion = mutableListOf<Usuario>()

  fun retornarUsuariosQueDieronLike() = usuariosQueDieronLikeALaPublicacion

  fun asignarPermiso(permisoAsignado : String){
    permiso = permisoAsignado
  }

  fun puedeVerPublicacionDe(usuario:Usuario,amigo : Usuario): Boolean {
    return when (permiso) {
      "solo amigos" -> {
        amigo.listaDeAmigos.contains(usuario)
      }
      "privado con lista de permitidos" -> {
        amigo.listaDePermitidos.contains(usuario)
      }
      "publico con lista de excluidos" -> {
        !amigo.listaDeExcluidos.contains(usuario)
      }
      else -> {
        return true
    }
  }
  }


  fun asignarCreador(usuario : Usuario) {
    creadorDeLaPublicacion = usuario
  }
  fun creador() = creadorDeLaPublicacion

  fun permisoDeLaPublicacion() = permiso

  abstract fun espacioQueOcupa(): Int

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

object FactorDeCompresion{
  var factorActual = 0.7
  fun cambiarFactor(valorNuevo : Double){
    factorActual = valorNuevo
  }

  fun valorDeCompresion() = factorActual
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  var factorDeCompresion = FactorDeCompresion.valorDeCompresion()
  override fun espacioQueOcupa() = ceil(alto * ancho * FactorDeCompresion.valorDeCompresion()).toInt()
}

class Texto(val contenido: String) : Publicacion() {

  override fun espacioQueOcupa() = contenido.length
}

class Video(val calidadDeVideo: CalidadVideo) : Publicacion() {
  override fun espacioQueOcupa() = calidadDeVideo.tamanioVideo()

}

