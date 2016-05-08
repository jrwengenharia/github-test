module Main (main) where
import System.IO (stdout, hSetBuffering, BufferMode(NoBuffering))
main :: IO ()
main = do hSetBuffering stdout NoBuffering
          putStrLn "Analise de salário\n"
          putStrLn "--------------------------------\n"
          putStrLn "Opcoes:\n"
          putStrLn "--------------------------------\n"
          putStrLn "1. Imposto\n"
          putStrLn "2. Novo salario\n"
          putStrLn "3. Classificacao\n"
          putStrLn "--------------------------------\n"
          putStrLn "Digite a opcao desejada: \n";
          opcao <- readLn;
          putStrLn "";
           case opcao of
              '1' -> do putStrLn "Calculo do imposto";
              '2' -> do putStrLn "Cálculo do novo salário";

              '3' -> do putStrLn "Classificação do salário";
              '0' -> return ();
               _ -> do putStrLn "Opcao invalida!\n";
