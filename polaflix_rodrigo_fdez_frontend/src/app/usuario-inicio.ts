export interface UsuarioInicio {
    nombreUsuario: string
    seriesPendientes: Serie[]
    seriesEmpezadas: Serie[]
    seriesTerminadas: Serie[]
}

interface Serie {
    id: number
    nombre: string
}