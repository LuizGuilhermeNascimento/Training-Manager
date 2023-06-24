export class ValidacaoFormulario {

    private static calcularDigitosVerificadores(c: string): number {

        let dig1 = 0;
        let dig2 = 0;
        let acc = 0;

        // cálculo do primeiro dígito
        for (let i = 0; i < 9; i++) {
            let dig_cpf = parseInt(c.substring(i,i+1));
            acc += dig_cpf*(10-i);
        }
        dig1 = 11 - (acc % 11);

        // cálculo do segundo dígito
        acc = 0;
        for (let i = 0; i < 10; i++) {
            let dig_cpf = parseInt(c.substring(i,i+1));
            acc += dig_cpf*(11-i);
        }
        dig2 = 11 - (acc % 11);
        if (dig1 >= 10) { dig1 = 0; }
        if (dig2 >= 10) { dig2 = 0; }

        return (dig1*10)+dig2;
    }

    public static validarCPF(cpf: string): boolean {
        let regex = "[^0-9]";
        cpf = cpf.replaceAll(regex, "");

        // verifica se o cpf possui 11 dígitos
        if (cpf.length != 11) {
            return false;
        }
        // verifica se todos os caracteres são iguais
        let allEquals = true;
        for (let i = 1; i < cpf.length; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allEquals = false;
            }
        }
        if (allEquals) { return false; }

        // verifica se os dígitos verificadores estão corretos
        let digitosVerif = ValidacaoFormulario.calcularDigitosVerificadores(cpf);
        if (parseInt(cpf.substring(9)) != digitosVerif) {
            return false;
        }

        return true;
    }

    public static validarEmail(email: string) {

        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (re.test(email)) {
            return true;
        } else {
            return false;
        }
    }
    public static validarSenha(senha: string): string {
        if (senha.length < 8) {
            return 'A senha deve ter no mínimo 8 caracteres.';
        }
        
        // Verificar se a senha contém letras maiúsculas, minúsculas, números e caracteres especiais
        const regexLetrasMaiusculas = /[A-Z]/;
        const regexLetrasMinusculas = /[a-z]/;
        const regexNumeros = /[0-9]/;
        const regexCaracteresEspeciais = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/;
    
        if (!regexLetrasMaiusculas.test(senha)) {
        return 'A senha deve conter pelo menos uma letra maiúscula.';
        }
    
        if (!regexLetrasMinusculas.test(senha)) {
        return 'A senha deve conter pelo menos uma letra minúscula.';
        }
    
        if (!regexNumeros.test(senha)) {
        return 'A senha deve conter pelo menos um número.';
        }
    
        if (!regexCaracteresEspeciais.test(senha)) {
        return 'A senha deve conter pelo menos um caractere especial.';
        }
    
        // Se a senha atender a todos os critérios, é considerada válida
        return 'A senha é válida';
    }

    public static validarCREF(cref: string) {

        const re = /^[A-Z]{2}\d{6}$/;

        if (re.test(cref)) {
            return true;
        } else {
            return false;
        }
    }
}