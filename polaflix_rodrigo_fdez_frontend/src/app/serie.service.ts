import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { SerieResumida } from './serie';

@Injectable({
  providedIn: 'root'
})
export class SerieService {

  seriesURL = 'http://localhost:8080/series';

  constructor(
    private http: HttpClient
  ) { }

  obtenerSeries(letraInicial: string, nombre?: string): Observable<SerieResumida[]> {
    let url = this.seriesURL;
    url += `?letraInicial=${letraInicial}`;
    if (nombre) {
      url += `?nombre=${nombre}`;
    }
    return this.http.get<SerieResumida[]>(url).pipe(
      tap(_ => console.log('Series encontradas')),
      catchError(this.handleError<SerieResumida[]>('obtenerSeries', []))
    );
  }

  obtenerSeriesQueEmpiezanPorNumero(): Observable<SerieResumida[]> {
    const url = `${this.seriesURL}/numero`;
    return this.http.get<SerieResumida[]>(url).pipe(
      tap(_ => console.log('Series que empizan por un numero encontradas')),
      catchError(this.handleError<SerieResumida[]>('obtenerobtenerSeriesQueEmpiezanPorNumero', []))
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
