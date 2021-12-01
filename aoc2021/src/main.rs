use crate::day01::run_day01;

mod day01;

fn main() {
    println!("Merry Christmas!");

    run_day01()
}

fn parse_to_i32_vec(input: String) -> Vec<i32> {
    input
        .split_whitespace()
        .filter_map(|w| w.parse().ok())
        .collect()
}
