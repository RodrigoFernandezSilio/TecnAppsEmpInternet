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

  private nombreUsuario = "usuario1";

  // BehaviorSubject que mantiene el estado del usuario, inicialmente null, emite el ultimo valor a nuevos suscriptores.
  private usuarioSubject = new BehaviorSubject<UsuarioInicio | null>(null);

  // Observable de solo lectura para suscribirse al estado del usuario, creado a partir del BehaviorSubject.
  usuario$ = this.usuarioSubject.asObservable();


  constructor(private http: HttpClient) {
    this.getUsuario(this.nombreUsuario).subscribe({
      next: (usuario) => {
        this.usuarioSubject.next(usuario);
        console.log(`Usuario inicial cargado`);
      },
      error: (err) => {
        console.error(`Error al cargar el usuario inicial: ${err}`);
      }
    });
  }

  /** GET usuario by id. Will 404 if id not found */
  getUsuario(usuarioID: string): Observable<UsuarioInicio> {
    console.log(`GET usuario con id=${usuarioID}`);
    const url = `${this.usuariosURL}/${usuarioID}`;
    return this.http.get<UsuarioInicio>(url).pipe(
      tap(_ => console.log(`fetched usuario id=${usuarioID}`)),
      catchError(this.handleError<UsuarioInicio>(`getUsuario id=${usuarioID}`))
    );
  }


  agregarSerieAEspacioPersonal(serieID: number): Observable<string> {
    const usuarioID = this.nombreUsuario;
    console.log('Añadiendo serie al espacio personal...');
    const url = `${this.usuariosURL}/${usuarioID}/series`;
    return this.http.patch<string>(url, serieID).pipe(
      tap(_ => {
        console.log(`Serie ${serieID} agregada al espacio personal del usuario ${usuarioID}`);
        // Llamada a getUsuario para actualizar el usuario después de agregar la serie
        this.getUsuario(usuarioID).subscribe({
          next: (usuario) => {
            this.usuarioSubject.next(usuario);
            console.log(`Usuario inicial cargado`);
          },
          error: (err) => {
            console.error(`Error al cargar el usuario inicial: ${err}`);
          }
        });
      }),
      catchError(this.handleError<string>(`agregarSerieAEspacioPersonal`))
    );
  }


  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(`UsuarioService: ${message}`);
    //this.messageService.add(`HeroService: ${message}`);
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

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
