export interface UserLogin {
    email: string;
    senha: string;
}

export interface UserJson {
    id: string;
    nome: string;
    cpf: string;
    email: string;
    roleId: number; // colocar no backend
}