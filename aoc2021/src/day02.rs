use std::fs;

use itertools::Itertools;

use crate::parse_to_i32_vec;

const INPUT_DIR: &str = "./input/day02/input.txt";

pub fn run_day02() {
    println!("Day 2");
    let raw_input =
        fs::read_to_string(INPUT_DIR).expect("Something went wrong reading the file");

    println!("Result Part A: {}", day02a(raw_input.clone()));
    println!("Result Part B: {}", day02b(raw_input.clone()));
}

fn day02a(input: String) -> i32 {
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

fn day02b(input: String) -> i32 {
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
    use super::*;

    const TEST_DIR: &str = "./input/day02/test.txt";

    #[test]
    fn test_day02a() {
        let raw_test_input =
            fs::read_to_string(TEST_DIR).expect("Something went wrong reading the file");
        assert_eq!(day02a(raw_test_input), 150);
    }

    #[test]
    fn test_day02b() {
        let raw_test_input =
            fs::read_to_string(TEST_DIR).expect("Something went wrong reading the file");
        assert_eq!(day02b(raw_test_input), 900);
    }
}
