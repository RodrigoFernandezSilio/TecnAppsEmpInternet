export interface SerieResumida {
    id: number
    nombre: string
    sinopsis: String
    creadores: Persona[]
    actores: Persona[]
}

interface Persona {
    nombre: string
    apellido1: string
    apellido2: string
}