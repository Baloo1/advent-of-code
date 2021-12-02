use crate::day01::run_day01;
use crate::day02::run_day02;

mod day01;
mod day02;

fn main() {
    println!("Merry Christmas!");

    run_day01();
    run_day02();
}

fn parse_to_i32_vec(input: String) -> Vec<i32> {
    input
        .split_whitespace()
        .filter_map(|w| w.parse().ok())
        .collect()
}
