defmodule Grades.CalculatorTest do
  use ExUnit.Case
  alias Grades.Calculator

  describe "percentage_grade" do
    test "testCase1" do
      assert 35 ==
               Calculator.percentage_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0.70,
                 final: 0.70
               })
    end

    test "testCase2" do
      assert 83 ==
               Calculator.percentage_grade(%{
                 homework: [1, 1],
                 labs: [1, 0.6, 1, 1],
                 midterm: 0.70,
                 final: 0.70
               })
    end
  end

  describe "letter_grade" do
    test "testCase3" do
      assert "EIN" ==
               Calculator.letter_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0.30,
                 final: 0.30
               })
    end

    test "testCase4" do
      assert "A+" ==
               Calculator.letter_grade(%{
                 homework: [0.85, 0.90, 0.90],
                 labs: [0.9, 0.9, 0.9],
                 midterm: 1,
                 final: 1
               })
    end

    test "testCase5" do
      assert "A" ==
               Calculator.letter_grade(%{
                 homework: [0.85, 0.86, 0.82],
                 labs: [0.9, 1, 0.83],
                 midterm: 0.87,
                 final: 0.88
               })
    end

    test "testCase6" do
      assert "A-" ==
               Calculator.letter_grade(%{
                 homework: [1, 1],
                 labs: [1, 0.6, 1, 1],
                 midterm: 0.70,
                 final: 0.70
               })
    end

    test "testCase7" do
      assert "B+" ==
               Calculator.letter_grade(%{
                 homework: [0.75, 0.75],
                 labs: [0.7, 0.6, 0.75, 0.8],
                 midterm: 0.75,
                 final: 0.78
               })
    end

    test "testCase8" do
      assert "B" ==
               Calculator.letter_grade(%{
                 homework: [0.7, 0.74],
                 labs: [0.7, 0.6, 1, 1],
                 midterm: 0.73,
                 final: 0.71
               })
    end

    test "testCase9" do
      assert "C+" ==
               Calculator.letter_grade(%{
                 homework: [0.8, 0.50],
                 labs: [0.65, 0.6, 0.7, 0.67],
                 midterm: 0.68,
                 final: 0.66
               })
    end

    test "testCase10" do
      assert "C" ==
               Calculator.letter_grade(%{
                 homework: [0.6, 0.5],
                 labs: [0.61, 0.6, 0.65, 0.62],
                 midterm: 0.63,
                 final: 0.64
               })
    end

    test "testCase11" do
      assert "D+" ==
               Calculator.letter_grade(%{
                homework: [0.56,0.53],
                labs: [0.56, 0.56, 0.57,0.49],
                midterm: 0.55,
                final: 0.545
               })
    end

    test "testCase12" do
      assert "D" ==
               Calculator.letter_grade(%{
                homework: [0.52,0.51],
                labs: [0.52, 0.49, 0.51,0.49],
                midterm: 0.51,
                final: 0.52
               })
    end

    test "testCase13" do
      assert "E" ==
               Calculator.letter_grade(%{
                homework: [0.42,0.43],
                labs: [0.4, 0.42, 0.44,0.41],
                midterm: 0.42,
                final: 0.44
               })
    end

    test "testCase14" do
      assert "F" ==
               Calculator.letter_grade(%{
                homework: [0.42,0.43],
                labs: [0.25, 0.25, 0.25,0.25],
                midterm: 0.42,
                final: 0.44
               })
    end
  end

  describe "numeric_grade" do
    test "testCase15" do
      assert 0 ==
               Calculator.numeric_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0.30,
                 final: 0.30
               })
    end

    test "testCase16" do
      assert 10 ==
               Calculator.numeric_grade(%{
                 homework: [0.85, 0.90, 0.90],
                 labs: [0.9, 0.9, 0.9],
                 midterm: 1,
                 final: 1
               })
    end

    test "testCase17" do
      assert 9 ==
               Calculator.numeric_grade(%{
                 homework: [0.85, 0.86, 0.82],
                 labs: [0.9, 1, 0.83],
                 midterm: 0.87,
                 final: 0.88
               })
    end

    test "testCase18" do
      assert 8 ==
               Calculator.numeric_grade(%{
                 homework: [1, 1],
                 labs: [1, 0.6, 1, 1],
                 midterm: 0.70,
                 final: 0.70
               })
    end

    test "testCase19" do
      assert 7 ==
               Calculator.numeric_grade(%{
                 homework: [0.75, 0.75],
                 labs: [0.7, 0.6, 0.75, 0.8],
                 midterm: 0.75,
                 final: 0.78
               })
    end

    test "testCase20" do
      assert 6 ==
               Calculator.numeric_grade(%{
                 homework: [0.7, 0.74],
                 labs: [0.7, 0.6, 1, 1],
                 midterm: 0.73,
                 final: 0.71
               })
    end

    test "testCase21" do
      assert 5 ==
               Calculator.numeric_grade(%{
                 homework: [0.8, 0.50],
                 labs: [0.65, 0.6, 0.7, 0.67],
                 midterm: 0.68,
                 final: 0.66
               })
    end

    test "testCase22" do
      assert 4 ==
               Calculator.numeric_grade(%{
                 homework: [0.6, 0.5],
                 labs: [0.61, 0.6, 0.65, 0.62],
                 midterm: 0.63,
                 final: 0.64
               })
    end

    test "testCase23" do
      assert 3 ==
               Calculator.numeric_grade(%{
                homework: [0.56,0.53],
                labs: [0.56, 0.56, 0.57,0.49],
                midterm: 0.55,
                final: 0.545
               })
    end

    test "testCase24" do
      assert 2 ==
               Calculator.numeric_grade(%{
                homework: [0.52,0.51],
                labs: [0.52, 0.49, 0.51,0.49],
                midterm: 0.51,
                final: 0.52
               })
    end

    test "testCase25" do
      assert 1 ==
               Calculator.numeric_grade(%{
                homework: [0.42,0.43],
                labs: [0.4, 0.42, 0.44,0.41],
                midterm: 0.42,
                final: 0.44
               })
    end

    test "testCase26" do
      assert 0 ==
               Calculator.numeric_grade(%{
                homework: [0.42,0.43],
                labs: [0.25, 0.25, 0.25,0.25],
                midterm: 0.42,
                final: 0.44
               })
    end
  end
end
