import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { UsuarioService } from './usuario.service';
import { UsuarioInicio } from './usuario-inicio';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, InicioComponent, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Polaflix';
  usuario: UsuarioInicio | null = null;

  constructor(private usuarioService: UsuarioService) {
  }

  ngOnInit(): void {
    this.usuarioService.usuario$.subscribe({
      next: (usuario) => {
        this.usuario = usuario;
        console.log('Usuario inicial obtenido en AppComponent:', usuario);
      },
      error: (error) => {
        console.error('Error al obtener el usuario inicial en AppComponent:', error);
      }
    });
  }
}
