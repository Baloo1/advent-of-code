import qualified Data.Set as Set

main :: IO ()
main = do
    input <- readFile "input.txt"
    print $ day1a $ parseInput input
    print $ day1b $ parseInput input

parseInput :: String -> [Integer]
parseInput input = map parseInt $ lines input

parseInt :: String -> Integer
parseInt ('+':xs) = read xs
parseInt xs = read xs

-- Problem a deserves consistency with problem b
day1a :: [Integer] -> Integer
day1a xs = sum xs

day1b :: [Integer] -> Integer
day1b xs = day1b' (cycle xs) 0 Set.empty
  where
    day1b' (x:xs) nbr set
      | nbr `Set.member` set = nbr
      | otherwise = day1b' xs (nbr + x) (Set.insert nbr set)