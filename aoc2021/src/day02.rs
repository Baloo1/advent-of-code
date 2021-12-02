use std::fs;

use itertools::Itertools;

const INPUT_DIR: &str = "./input/day02/input.txt";

pub fn run_day02() {
    println!("Day 2");
    println!("Result Part A: {}", day02a(INPUT_DIR));
    println!("Result Part B: {}", day02b(INPUT_DIR));
}

fn day02a(dir: &str) -> i32 {
    let input =
        fs::read_to_string(dir).expect("Something went wrong reading the file");

    let mut pos: i32 = 0;
    let mut depth: i32 = 0;
    for x in input.lines() {
        let i: (&str, &str) = x.splitn(2, ' ').collect_tuple().unwrap();
        match i.0 {
            "forward" => {
                pos += i.1.parse::<i32>().unwrap()
            }
            "down" => {
                depth += i.1.parse::<i32>().unwrap()
            }
            "up" => {
                depth -= i.1.parse::<i32>().unwrap()
            }
            _ => {
                unreachable!("WHAT ARE YOU DOING HERE?")
            }
        }
    }
    depth * pos
}

fn day02b(dir: &str) -> i32 {
    let input =
        fs::read_to_string(dir).expect("Something went wrong reading the file");

    let mut pos: i32 = 0;
    let mut depth: i32 = 0;
    let mut aim = 0;
    for x in input.lines() {
        let i: (&str, &str) = x.splitn(2, ' ').collect_tuple().unwrap();
        match i.0 {
            "forward" => {
                pos += i.1.parse::<i32>().unwrap();
                depth += aim * i.1.parse::<i32>().unwrap()
            }
            "down" => {
                aim += i.1.parse::<i32>().unwrap()
            }
            "up" => {
                aim -= i.1.parse::<i32>().unwrap()
            }
            _ => {
                unreachable!("WHAT ARE YOU DOING HERE?")
            }
        }
    }
    depth * pos
}

#[cfg(test)]
mod tests {
    extern crate test;

    use test::Bencher;

    use super::*;

    const TEST_DIR: &str = "./input/day02/test.txt";

    #[test]
    fn test_day02a() {
        assert_eq!(day02a(TEST_DIR), 150);
    }

    #[test]
    fn test_day02b() {
        assert_eq!(day02b(TEST_DIR), 900);
    }

    #[bench]
    fn bench_day02a(b: &mut Bencher) { b.iter(|| day02a(INPUT_DIR)); }

    #[bench]
    fn bench_day02b(b: &mut Bencher) { b.iter(|| day02b(INPUT_DIR)); }

    #[bench]
    fn bench_day02(b: &mut Bencher) { b.iter(|| run_day02()); }
}
