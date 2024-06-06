import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, of, tap } from 'rxjs';
import { UsuarioInicio } from './usuario-inicio';
import { SerieResumida } from './serie';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuariosURL = 'http://localhost:8080/usuarios';

  // usuarioID por defecto dado que la autenticacion no se ha implementado.
  private usuarioID_porDefecto = "usuario1";

  // BehaviorSubject que mantiene el estado del usuario, inicialmente null, emite el ultimo valor a nuevos suscriptores.
  private usuarioSubject = new BehaviorSubject<UsuarioInicio | null>(null);

  // Observable de solo lectura para suscribirse al estado del usuario, creado a partir del BehaviorSubject.
  usuario$ = this.usuarioSubject.asObservable();


  constructor(private http: HttpClient) {
    // Obtener usuario por defecto
    this.getUsuario(this.usuarioID_porDefecto).subscribe({
      next: (usuario) => {
        this.usuarioSubject.next(usuario);
        console.log(`Usuario inicial cargado`);
      },
      error: (err) => {
        console.error(`Error al cargar el usuario inicial: ${err}`);
      }
    });
  }

  /** Obtiene un usuario por su ID. Devuelve un error 404 si no se encuentra un usuario con ese ID */
  getUsuario(usuarioID: string): Observable<UsuarioInicio> {
    console.log(`Obteniendo usuario...`);

    const url = `${this.usuariosURL}/${usuarioID}`;

    return this.http.get<UsuarioInicio>(url).pipe(
      tap(_ => console.log(`Usuario ${usuarioID} obtenido`)),
      catchError(this.handleError<UsuarioInicio>(`getUsuario id=${usuarioID}`))
    );
  }

  /** Agrega una serie al espacio personal del usuario.
   * Devuelve un error 404 si no encuentra un usuario con ese ID o una serie con ese ID. */
  agregarSerieAEspacioPersonal(serieID: number): Observable<string> {
    console.log('Añadiendo serie al espacio personal...');

    const usuarioID = this.usuarioID_porDefecto;
    const url = `${this.usuariosURL}/${usuarioID}/series`;

    return this.http.patch<string>(url, serieID).pipe(
      tap(_ => {
        console.log(`Serie ${serieID} agregada al espacio personal del usuario ${usuarioID}`);
        // Llamada a getUsuario para actualizar el usuario después de agregar la serie
        this.getUsuario(usuarioID).subscribe({
          next: (usuario) => {
            this.usuarioSubject.next(usuario);
            console.log(`Usuario cargado`);
          },
          error: (err) => {
            console.error(`Error al cargar el usuario: ${err}`);
          }
        });
      }),
      catchError(this.handleError<string>(`agregarSerieAEspacioPersonal usuarioID=${usuarioID} serieID=${serieID}`))
    );
  }


  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
