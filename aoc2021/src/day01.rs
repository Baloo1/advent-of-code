use std::fs;
use std::ops::AddAssign;

use crate::parse_to_i32_vec;

const INPUT_DIR: &str = "./input/day01/input.txt";

pub fn run_day01() {
    println!("Day 1");
    println!("Result Part A: {}", day01a(INPUT_DIR));
    println!("Result Part B Unsafe: {}", unsafe_day01b(INPUT_DIR));
    println!("Result Part B: {}", day01b(INPUT_DIR));
}

fn day01a(dir: &str) -> i32 {
    let raw_input =
        fs::read_to_string(dir).expect("Something went wrong reading the file");
    let input: Vec<i32> = parse_to_i32_vec(raw_input);

    let mut result = 0;
    let mut prev = i32::MAX;
    for x in input {
        if x > prev {
            result.add_assign(1);
        }
        prev = x;
    }
    result
}

fn unsafe_day01b(dir: &str) -> i32 {
    let raw_input =
        fs::read_to_string(dir).expect("Something went wrong reading the file");
    let input: Vec<i32> = parse_to_i32_vec(raw_input);

    let mut result = 0;
    unsafe {
        let window_size = 3;
        let mut ptr = input.as_ptr();
        let input_end = &ptr.offset(input.len() as isize - window_size);
        while !ptr.eq(input_end) {
            let curr = *ptr.offset(window_size);
            let prev = *ptr;
            if curr > prev {
                result.add_assign(1)
            }
            ptr = ptr.offset(1);
        }
        return result;
    }
}

fn day01b(dir: &str) -> i32 {
    let raw_input =
        fs::read_to_string(dir).expect("Something went wrong reading the file");
    let input: Vec<i32> = parse_to_i32_vec(raw_input);

    let mut result = 0;
    let window_size = 3;
    for (index, value) in input.iter().enumerate() {
        if index + window_size + 1 > input.len() {
            return result;
        }
        let curr = input.get(index + window_size).unwrap();
        let prev = value;
        if curr > prev {
            result.add_assign(1)
        }
    }
    result
}

#[cfg(test)]
mod tests {
    extern crate test;

    use test::Bencher;

    use super::*;

    const TEST_DIR: &str = "./input/day01/test.txt";

    #[test]
    fn test_day01a() {
        assert_eq!(day01a(TEST_DIR), 7);
    }

    #[test]
    fn test_day01b() {
        assert_eq!(5, day01b(TEST_DIR));
    }

    #[test]
    fn test_unsafe_day01b() {
        assert_eq!(5, unsafe_day01b(TEST_DIR));
    }

    #[bench]
    fn bench_day01a(b: &mut Bencher) {
        b.iter(|| day01a(TEST_DIR));
    }

    #[bench]
    fn bench_unsafe_day01b(b: &mut Bencher) {
        b.iter(|| unsafe_day01b(INPUT_DIR));
    }

    #[bench]
    fn bench_day01b(b: &mut Bencher) {
        b.iter(|| day01b(INPUT_DIR));
    }

    #[bench]
    fn bench_day01(b: &mut Bencher) {
        b.iter(|| run_day01());
    }
}
