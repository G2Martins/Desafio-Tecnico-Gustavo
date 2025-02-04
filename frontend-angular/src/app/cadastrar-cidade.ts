import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Cidade } from "@domain/cidade";
import { ProjetoService } from "@service/projeto-service";
import { MessageService } from "primeng/api";

import { DialogModule } from "primeng/dialog";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { RadioButtonModule } from "primeng/radiobutton";
import { FormsModule } from "@angular/forms";

@Component({
    selector: "cadastrar-cidade",
    templateUrl: "cadastrar-cidade.html",
    standalone: true,
    imports: [
        DialogModule, 
        ButtonModule, 
        InputTextModule, 
        RadioButtonModule, 
        FormsModule 
    ],
    providers: [ProjetoService, MessageService],
})
export class CadastrarCidade {
    @Input() public cidade: Cidade = new Cidade();
    @Output("onClose") private eventoFechaJanela = new EventEmitter<boolean>();

    constructor(
        private service: ProjetoService,
        private messageService: MessageService
    ) {}

    public salvar(): void {
        this.service.salvar(this.cidade).subscribe({
            next: () => {
                this.messageService.add({
                    severity: "success",
                    summary: "Info",
                    detail: `Cidade '${this.cidade.nome}' cadastrada com sucesso!`,
                });
                this.cancelar();
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "Erro",
                    detail: `Erro ao salvar a cidade!`,
                });
            },
        });
    }

    public cancelar(): void {
        this.eventoFechaJanela.emit(true);
    }
}
