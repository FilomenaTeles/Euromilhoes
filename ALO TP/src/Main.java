import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    static List<Chave> boletim = new ArrayList<>();
    static List<Iguais> iguais = new ArrayList<>();
    static Chave chavePremeada = new Chave();
    static Random rnd = new Random();

    //ARRAYS QUE VAO CONTAR QUANTOS NUM E ESTRELAS CADA BOLETIM TEM IGUAL A CHAVE
    static int[] numsIguais;
    static int[] estrelasIguais;

    public static void main(String[] args) {
        Euromilhoes();

    }

    private static void Euromilhoes() {
        in = new Scanner(System.in);

        Chave aposta = new Chave();
        int op = 1;
        while (op != 0) {
            System.out.println("Euromilhoes:");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Fazer sorteio");
            System.out.println("2 - Fazer aposta com chave escolhida");
            System.out.println("3 - Fazer aposta com chave aleatória");
            System.out.println("4 - Ver probabilidade de ganhar");
            System.out.println("0 - Sair");
            try {
                op = in.nextInt();

                switch (op) {
                    case 0:
                        break;
                    case 1:
                        Sorteio();
                        break;
                    case 2:
                        ApostaEscolha();
                        break;
                    case 3:
                        ApostaAleatoria();
                        break;
                    case 4:
                        VerProb();
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }

            } catch (Exception e) {
                System.out.println("Valor inválido, tente novamente");
                in.nextLine();
            }
        }
    }

    private static void Sorteio() {
        if (boletim.size() == 0) { //SE NÃO TIVER APOSTAS NÃO DEIXA FAZER SORTEIO
            System.out.println("Sem apostas feitas. Faça uma aposta primeiro.");
            return;
        } else {//GERAR NUMEROS ALEATORIOS

            //GERAR E GUARDAR CHAVE PREMIADA
            chavePremeada = GerarChave();

            System.out.println("Chave vencedora:");
            System.out.println(chavePremeada);
            System.out.println();
            System.out.println("********************");

            //COMPARAR CHAVE COM AS APOSTAS
            CompararChaveAposta();

            //VERIFICAR PRÉMIOS
            VerPremios();
            //FAZ O RESET A LISTA DE NºS IGUAIS
            for (int i = 0; i <iguais.size() ; i++) {
                iguais.get(i).setN1(0);
                iguais.get(i).setN2(0);
                iguais.get(i).setN3(0);
                iguais.get(i).setN4(0);
                iguais.get(i).setN5(0);
                iguais.get(i).setE1(0);
                iguais.get(i).setE2(0);
            }
        }
    }

    private static void VerPremios() {
        int contador = 0;
        for (int i = 0; i < boletim.size(); i++) {
            //1o PREMIO 5 NUMS 2 ESTRELAS
            if (numsIguais[i] == 5 && estrelasIguais[i] == 2) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 1o Prémio\n" +
                        "Valor: 40.000.000,00 €");
                System.out.println("Numeros iguais:");
                System.out.println(iguais.get(i).getN1());
                System.out.println(iguais.get(i).getN2());
                System.out.println(iguais.get(i).getN3());
                System.out.println(iguais.get(i).getN4());
                System.out.println(iguais.get(i).getN5());
                System.out.println("Estrelas iguais:");
                System.out.println(iguais.get(i).getE1());
                System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //2º PREMIO 5 NUMS 1 ESTRELA
            else if (numsIguais[i] == 5 && estrelasIguais[i] == 1) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 2o Prémio\n" +
                        "Valor: 109.395,77 €");
                System.out.println("Numeros iguais:");
                System.out.println(iguais.get(i).getN1());
                System.out.println(iguais.get(i).getN2());
                System.out.println(iguais.get(i).getN3());
                System.out.println(iguais.get(i).getN4());
                System.out.println(iguais.get(i).getN5());
                System.out.println("Estrela igual:");
                if (iguais.get(i).getE1() != 0) {
                    System.out.println(iguais.get(i).getE1());
                } else System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //3º PREMIO 5 NUMS 0 ESTRELAS
            else if (numsIguais[i] == 5 && estrelasIguais[i] == 0) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 3o Prémio\n" +
                        "Valor: 7.989,87 €");
                System.out.println("Numeros iguais:");
                System.out.println(iguais.get(i).getN1());
                System.out.println(iguais.get(i).getN2());
                System.out.println(iguais.get(i).getN3());
                System.out.println(iguais.get(i).getN4());
                System.out.println(iguais.get(i).getN5());
                System.out.println();

                contador++;
            }
            //4ºPREMIO 4 NUMS 2 ESTRELAS
            else if (numsIguais[i] == 4 && estrelasIguais[i] == 2) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 4o Prémio\n" +
                        "Valor: 926,00  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println("Estrelas iguais:");
                System.out.println(iguais.get(i).getE1());
                System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //5º PREMIO 4 NUMS 1 ESTRELA
            else if (numsIguais[i] == 4 && estrelasIguais[i] == 1) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 5o Prémio\n" +
                        "Valor: 72,05  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println("Estrela igual:");
                if (iguais.get(i).getE1() != 0) {
                    System.out.println(iguais.get(i).getE1());
                } else System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //6º PREMIO 3 NUMS 2 ESTRELAS
            else if (numsIguais[i] == 3 && estrelasIguais[i] == 2) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 6o Prémio\n" +
                        "Valor: 38,38 €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println("Estrelas iguais:");
                System.out.println(iguais.get(i).getE1());
                System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //7º PREMIO 4 NUMS 0 ESTRELAS
            else if (numsIguais[i] == 4 && estrelasIguais[i] == 0) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 7o Prémio\n" +
                        "Valor: 21,53  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println();

                contador++;
            }
            //8º PREMIO 2 NUMS 2 ESTRELAS
            else if (numsIguais[i] == 2 && estrelasIguais[i] == 2) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 8o Prémio\n" +
                        "Valor: 11,19  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println("Estrelas iguais:");
                System.out.println(iguais.get(i).getE1());
                System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //9º PREMIO 3 NUMS 1 ESTRELA
            else if (numsIguais[i] == 3 && estrelasIguais[i] == 1) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 9o Prémio\n" +
                        "Valor: 7,92  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println("Estrela igual:");
                if (iguais.get(i).getE1() != 0) {
                    System.out.println(iguais.get(i).getE1());
                } else System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //10º PREMIO 3 NUMS 0 ESTRELAS
            else if (numsIguais[i] == 3 && estrelasIguais[i] == 0) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 10o Prémio\n" +
                        "Valor:  6,29  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println();

                contador++;
            }
            //11º PREMIO 1 NUM 2 ESTRELAS
            else if (numsIguais[i] == 1 && estrelasIguais[i] == 2) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 11o Prémio\n" +
                        "Valor: 6,24  €");
                System.out.println("Numero igual:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println("Estrelas iguais:");
                System.out.println(iguais.get(i).getE1());
                System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //12º PREMIO 2 NUMS 1 ESTRELA
            else if (numsIguais[i] == 2 && estrelasIguais[i] == 1) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 12o Prémio\n" +
                        "Valor: 4,62  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println("Estrela igual:");
                if (iguais.get(i).getE1() != 0) {
                    System.out.println(iguais.get(i).getE1());
                } else System.out.println(iguais.get(i).getE2());
                System.out.println();

                contador++;
            }
            //13º PREMIO 2 NUMS 0 ESTRELAS
            else if (numsIguais[i] == 2 && estrelasIguais[i] == 0) {
                System.out.println("Boletim " + (i + 1) + ": ");
                System.out.println("Parabens ganhou o 13o Prémio\n" +
                        "Valor:  3,17  €");
                System.out.println("Numeros iguais:");
                if (iguais.get(i).getN1() != 0) {
                    System.out.println(iguais.get(i).getN1());
                }
                if (iguais.get(i).getN2() != 0) {
                    System.out.println(iguais.get(i).getN2());
                }
                if (iguais.get(i).getN3() != 0) {
                    System.out.println(iguais.get(i).getN3());
                }
                if (iguais.get(i).getN4() != 0) {
                    System.out.println(iguais.get(i).getN4());
                }
                if (iguais.get(i).getN5() != 0) {
                    System.out.println(iguais.get(i).getN5());
                }
                System.out.println();

                contador++;
            }
        }
        if (contador == 0){
            System.out.println("Não teve nenhum premio");
        }

    }

    private static void CompararChaveAposta() {
        numsIguais = new int[boletim.size()];
        estrelasIguais = new int[boletim.size()];

        //ADICIONAR ELEMENTOS A LISTA IGUAIS - MESMO Nº DE BOLETINS
        for (int i = 0; i < boletim.size(); i++) {
            iguais.add(new Iguais());
        }

        for (int i = 0; i < boletim.size(); i++) { //PRECORRER TD O BOLETIM
            for (int j = 0; j < 5; j++) { //COMPARAR NUMS
                for (int k = 0; k < chavePremeada.getNumeros().length; k++) { //COMPARAR CHAVE SORTEADA
                    if (boletim.get(i).getNumeros()[j] == chavePremeada.getNumeros()[k]) {
                        numsIguais[i]++; //ADICIONA NO CONTADOR NO ARRAY NA POSICAO DO BELETIM QUE ESTA A SER COMPARADO
                        if (j == 0) {       //GUARDA OS NUMS QUE SÃO IGUAIS A CHAVE PREMIADA
                            iguais.get(i).setN1(boletim.get(i).getNumeros()[j]);
                        } else if (j == 1) {
                            iguais.get(i).setN2(boletim.get(i).getNumeros()[j]);
                        } else if (j == 2) {
                            iguais.get(i).setN3(boletim.get(i).getNumeros()[j]);
                        } else if (j == 3) {
                            iguais.get(i).setN4(boletim.get(i).getNumeros()[j]);
                        } else iguais.get(i).setN5(boletim.get(i).getNumeros()[j]);
                    }
                }
            }
            for (int j = 0; j < 2; j++) { //COMPARAR ESTRELAS
                for (int k = 0; k < chavePremeada.getEstrelas().length; k++) { //COMPARAR CHAVE SORTEADA
                    if (boletim.get(i).getEstrelas()[j] == chavePremeada.getEstrelas()[k]) {
                        estrelasIguais[i]++; //ADICIONA NO CONTADOR NO ARRAY NA POSICAO DO BELETIM QUE ESTA A SER COMPARADO
                        if (j == 0) {       //GUARDA OS NUMS QUE SÃO IGUAIS A CHAVE PREMIADA
                            iguais.get(i).setE1(boletim.get(i).getEstrelas()[j]);
                        } else iguais.get(i).setE2(boletim.get(i).getEstrelas()[j]);
                    }
                }
            }
        }
    }

    private static Chave GerarChave() {
        int[] numerosAlt = new int[5];
        int[] estrelasAlt = new int[2];

        //PREENCE OS NUMEROS
        for (int i = 0; i < 5; i++) {
            numerosAlt[i] = rnd.nextInt(1, 51);
            for (int j = 0; j < numerosAlt.length; j++) { //VERIFICA SE HÁ NUMEROS REPETIDOS
                if (numerosAlt[i] == numerosAlt[j] && i != j && numerosAlt[j] != 0) {
                    numerosAlt[i] = rnd.nextInt(1, 51);
                }
            }
        }
        //PREENCHE AS ESTRELAS
        for (int i = 0; i < 2; i++) {
            estrelasAlt[i] = rnd.nextInt(1, 13);
            while (estrelasAlt[1] == estrelasAlt[0]) { //VERIFICA SE TEM ESTRELAS IGUIAS
                estrelasAlt[1] = rnd.nextInt(1, 13);
            }
        }
        //ORDENAR NUMEROS
        Arrays.sort(numerosAlt);

        // ORDENAR ESTRELAS
        Arrays.sort(estrelasAlt);
        Chave chaveAlt = new Chave(numerosAlt, estrelasAlt);
        return chaveAlt;
    }

    private static void ApostaEscolha() {
        int num, estrela;
        System.out.println("Quantas apostas deseja fazer? (1 - 5)");
        try {
            int aposta = in.nextInt();
            if (aposta >= 1 && aposta <= 5) {
                for (int i = 0; i < aposta; i++) { //PREENCHER OS BOLETINS
                    int[] numeros = new int[5];
                    int[] estrelas = new int[2];
                    System.out.println("Boletim " + (i + 1) + ": ");
                    for (int j = 0; j < 5; j++) { //PREENCHE 1 BOLETIM DE CADA VEZ - NUMS

                        System.out.println("Qual o " + (j + 1) + " numero (1-50)?");
                        num = in.nextInt();
                        if (num >= 1 && num <= 50) { //VERIFICA INTREVALO
                            numeros[j] = num;

                            for (int k = 0; k < numeros.length; k++) { //VERIFICA SE HÁ NUMEROS REPETIDOS
                                if (numeros[j] == numeros[k] && j != k && numeros[k] != 0) {
                                    System.out.println("Numero já escolhido. EScolha um numero diferente");
                                    j--; //VOLTA A POSICAO EM QUE TENTOU INSERIR UM NUM IGUAL
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Numero inválido. Escolha um numero entre 1 e 50");
                            j--;    //VOLTA A POSICAO EM QUE TENTOU INSERIR UM NUM INVALIDO
                        }
                    }
                    //PREENCHE AS ESTRELAS
                    for (int j = 0; j < 2; j++) {
                        System.out.println("Qual a " + (j + 1) + " estrela?");
                        estrelas[j] = in.nextInt();
                        if (estrelas[j] > 1 && estrelas[j] <= 12) {
                            while (estrelas[1] == estrelas[0]) { //VERIFICA SE TEM ESTRELAS IGUIAS
                                System.out.println("Estrela já escolhida. Escolha uma estrela diferente");
                                estrelas[1] = in.nextInt();
                            }
                        } else {
                            System.out.println("Numero inválido. Escolha um numero entre 1 e 12");
                            j--;    //VOLTA A POSICAO EM QUE TENTOU INSERIR UM NUM INVALIDO
                        }
                    }
                    //ORDENAR APOSTA
                    Arrays.sort(numeros);
                    Arrays.sort(estrelas);
                    //ADICIONAR A LISTA DE BOLETINS
                    boletim.add(new Chave(numeros, estrelas));
                }
                System.out.println("Apostas efetuadas com sucesso.");
                //APRESENTA AS APOSTAS FEITAS
                VerAposta();

            } else System.out.println("Valor inválido.");
        } catch (Exception e) {
            System.out.println("Valor inválido, tente novamente");
            in.nextLine();
        }
    }

    private static void VerAposta() {
        for (int i = 0; i < boletim.size(); i++) {
            System.out.println("Boletim " + (i + 1) + ": ");
            System.out.println(boletim.get(i));
        }
    }

    private static void ApostaAleatoria() {
        System.out.println("Quantas apostas deseja fazer? (1 - 5)");
        try {
            int aposta = in.nextInt();
            if (aposta >= 1 && aposta <= 5) {
                for (int i = 0; i < aposta; i++) { //PREENCHER OS BOLETINS
                    boletim.add(new Chave(GerarChave().getNumeros(), GerarChave().getEstrelas()));
                }
                VerAposta();
            } else System.out.println("Valor inválido.");
        } catch (Exception e) {
            System.out.println("Valor inválido, tente novamente");
            in.nextLine();
        }
    }

    private static void VerProb() {
        int op, contador = 0, numsIguais, estrelasIguais;
        double prob;
        Chave chaveTestar = new Chave();
        Chave chaveTeste;
        int[] cont;   //CONTA QUANTOS NUM E ESTRELAS SÃO IGUAIS A CHAVE PREMIADA
        boolean flag = true;

        //PEDIR CHAVE A TESTAR
        System.out.println("Com que chave deseja testar a probabilidade de ganhar um dos 5 primeiros prémios?");
        System.out.println("1 - Chave escolhida");
        System.out.println("2 - Chave aleatória");
        System.out.println("0 - Voltar menu anterior");
        try {
            op = in.nextInt();

            switch (op) {
                case 0:
                    break;
                case 1:
                    chaveTestar = EscolherChaveProb();
                    break;

                case 2:
                    chaveTestar = GerarChave();
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
            System.out.println(chaveTestar);
            while (flag) {
                chaveTeste = GerarChave(); //CHAVE ALEATORIA PREMEADA. VAI SER COMPARADA COM A CHAVE FIXA

                //COMPARAR NUMS
                cont = Comparar(chaveTeste, chaveTestar);
                numsIguais = cont[0];
                estrelasIguais = cont[1];

                if (numsIguais == 5 && estrelasIguais == 2) {
                    System.out.println("Saiu o 1º premio ao fim de:");
                    prob=contador;
                    System.out.println(contador +" sorteios (probabilidade= "+(1/prob)+")");
                    System.out.println("Isso corresponde a ");
                    Convertor(contador);
                    flag = false;
                }/*else if (numsIguais == 5 && estrelasIguais == 1) {
                    System.out.println("Saiu o 2º premio ao fim de:");
                    System.out.println(contador +" sorteios (probabilidade= "+(1/prob)+")");
                    System.out.println("Isso corresponde a ");
                    Convertor(contador);
                    System.out.println("Fazer uma nova tentativa?");
                    System.out.println("1-sim");
                    System.out.println("2-nao");

                    int opp = in.nextInt();
                    switch (opp) {
                        case 1:
                            flag = true;
                            contador=0;
                            break;
                        case 2:
                            flag = false;
                            break;
                        default:
                            System.out.println("Valor inválido, tente novamente");
                    }


                } else if (numsIguais == 5 && estrelasIguais == 0) {
                    System.out.println("Saiu o 3º premio ao fim de:");
                    System.out.println(contador +" sorteios (probabilidade= "+(1/prob)+")");
                    System.out.println("Isso corresponde a ");
                    Convertor(contador);
                    System.out.println("Fazer uma nova tentativa?");
                    System.out.println("1-sim");
                    System.out.println("2-nao");

                    int opp = in.nextInt();
                    switch (opp) {
                        case 1:
                            flag = true;
                            contador=0;
                            break;
                        case 2:
                            flag = false;
                            break;
                        default:
                            System.out.println("Valor inválido, tente novamente");
                    }


                } else if (numsIguais == 4 && estrelasIguais == 2) {
                    System.out.println("Saiu o 4º premio ao fim de:");
                    System.out.println(contador +" sorteios (probabilidade= "+(1/prob)+")");
                    System.out.println("Isso corresponde a ");
                    Convertor(contador);
                    System.out.println("Fazer uma nova tentativa?");
                    System.out.println("1-sim");
                    System.out.println("2-nao");

                    int opp = in.nextInt();
                    switch (opp) {
                        case 1:
                            flag = true;
                            contador=0;
                            break;
                        case 2:
                            flag = false;
                            break;
                        default:
                            System.out.println("Valor inválido, tente novamente");
                    }


                } else if (numsIguais == 4 && estrelasIguais == 1) {
                    System.out.println("Saiu o 5º premio ao fim de:");
                    System.out.println(contador +" sorteios (probabilidade= "+(1/prob)+")");
                    System.out.println("Isso corresponde a ");
                    Convertor(contador);
                    System.out.println("Fazer uma nova tentativa?");
                    System.out.println("1-sim");
                    System.out.println("2-nao");
                    int opp = in.nextInt();
                    switch (opp) {
                        case 1:
                            flag = true;
                            contador=0;
                            break;
                        case 2:
                            flag = false;
                            break;
                        default:
                            System.out.println("Valor inválido, tente novamente");
                    }

                }*/ else contador++;
            }
        } catch (Exception e) {
            System.out.println("Valor inválido, tente novamente");
            in.nextLine();
        }
    }

    private static void Convertor(int contador) {
        //1 ANO TEM 52 SEMANAS
        //1 ANO TEM 104 SORTEIOS
        int anos=contador/104;
        int mes=(contador%104)/8;
        int semana=((contador%104)%8)/2;
        int dias =contador%2;

        if (dias>0){
            System.out.println(anos+" anos, "+mes+" meses, "+ semana+" semanas e "+dias+" dia para ganhar o 1º prémio, usando a mesma chave.");
        }else System.out.println("São necesários "+anos+" anos, "+mes+" meses e "+ semana+" semanas para ganhar o 1º prémio, usando a mesma chave.");
    }

    private static int[] Comparar(Chave chaveTeste, Chave chaveTestar) {
        int numsIguais = 0, estrelasIguais = 0;


        for (int j = 0; j < 5; j++) { //COMPARAR NUMS
            for (int k = 0; k < chaveTeste.getNumeros().length; k++) { //COMPARAR CHAVE SORTEADA
                if (chaveTestar.getNumeros()[j] == chaveTeste.getNumeros()[k]) {
                    numsIguais++; //ADICIONA NO CONTADOR
                }
            }
        }
        for (int j = 0; j < 2; j++) { //COMPARAR ESTRELAS
            for (int k = 0; k < chaveTeste.getEstrelas().length; k++) { //COMPARAR CHAVE SORTEADA
                if (chaveTestar.getEstrelas()[j] == chaveTeste.getEstrelas()[k]) {
                    estrelasIguais++; //ADICIONA NO CONTADOR
                }
            }
        }
        int[] contador = new int[2];
        contador[0] = numsIguais;
        contador[1] = estrelasIguais;
        return contador;
    }

    private static Chave EscolherChaveProb() {
        int num;
        int[] numeros = new int[5];
        int[] estrelas = new int[2];

        for (int j = 0; j < 5; j++) { //PREENCHE NUMS

            System.out.println("Qual o " + (j + 1) + " numero (1-50)?");
            try {
                num = in.nextInt();
                if (num >= 1 && num <= 50) { //VERIFICA INTREVALO
                    numeros[j] = num;

                    for (int k = 0; k < numeros.length; k++) { //VERIFICA SE HÁ NUMEROS REPETIDOS
                        if (numeros[j] == numeros[k] && j != k && numeros[k] != 0) {
                            System.out.println("Numero já escolhido. EScolha um numero diferente");
                            j--; //VOLTA A POSICAO EM QUE TENTOU INSERIR UM NUM IGUAL
                            break;
                        }
                    }
                } else {
                    System.out.println("Numero inválido. Escolha um numero entre 1 e 50");
                    j--;    //VOLTA A POSICAO EM QUE TENTOU INSERIR UM NUM INVALIDO
                }
            } catch (Exception e) {
                System.out.println("Valor inválido, tente novamente");
                in.nextLine();
            }
        }
        //PREENCHE AS ESTRELAS
        for (int j = 0; j < 2; j++) {
            System.out.println("Qual a " + (j + 1) + " estrela?");
            try {
                estrelas[j] = in.nextInt();
                if (estrelas[j] > 1 && estrelas[j] <= 12) {
                    while (estrelas[1] == estrelas[0]) { //VERIFICA SE TEM ESTRELAS IGUIAS
                        System.out.println("Estrela já escolhida. Escolha uma estrela diferente");
                        estrelas[1] = in.nextInt();
                    }
                } else {
                    System.out.println("Estrela inválida. Escolha um numero entre 1 e 12");
                    j--;    //VOLTA A POSICAO EM QUE TENTOU INSERIR UM NUM INVALIDO
                }
            } catch (Exception e) {
                System.out.println("Valor inválido, tente novamente");
                in.nextLine();
            }
        }
        Arrays.sort(numeros);
        Arrays.sort(estrelas);
        Chave chaveProb = new Chave(numeros, estrelas);
        return chaveProb;
    }
}