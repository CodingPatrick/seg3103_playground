defmodule Grades.Calculator do

  # Helper method for Question 2.1
  def avg(grades) do
    if Enum.count(grades) == 0 do
      0
    else
      Enum.sum(grades) / Enum.count(grades)
    end
  end

  # Helper method for Question 2.2
  def failed_to_participate(avg_homework, avg_exams, num_labs) do
    if avg_homework < 0.4 || avg_exams < 0.4 || num_labs < 3 do
      true
    else 
      false
    end
  end

  # Helper method for Question 2.3
  def calculate_grade(avg_labs, avg_homework, midterm, final) do
    0.2 * avg_labs + 0.3 * avg_homework + 0.2 * midterm + 0.3 * final
  end

  # Helper method 1 for Question 2.4
  def filter_and_count(array, thresehold) do
    array
    |> Enum.reject(fn mark -> mark < thresehold end)
    |> Enum.count()
  end

  # Helper method 2 for Question 2.4
  def letter_numeric(mark, indexToUse) do
      cond do
        mark > 0.895 -> Enum.at(indexToUse,0)
        mark > 0.845 -> Enum.at(indexToUse,1)
        mark > 0.795 -> Enum.at(indexToUse,2)
        mark > 0.745 -> Enum.at(indexToUse,3)
        mark > 0.695 -> Enum.at(indexToUse,4)
        mark > 0.645 -> Enum.at(indexToUse,5)
        mark > 0.595 -> Enum.at(indexToUse,6)
        mark > 0.545 -> Enum.at(indexToUse,7)
        mark > 0.495 -> Enum.at(indexToUse,8)
        mark > 0.395 -> Enum.at(indexToUse,9)
        :else -> Enum.at(indexToUse,10)
      end
  end

  # Refactored code that was provided as part of the homework
  def percentage_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(homework)
    avg_labs = avg(labs)
    mark = calculate_grade(avg_labs, avg_homework, midterm, final)
    round(mark * 100)
  end

  def letter_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(homework)
    avg_labs = avg(labs)
    avg_exams = (midterm + final) / 2
    num_labs = filter_and_count(labs, 0.25)

    ftp = failed_to_participate(avg_homework, avg_exams, num_labs)
    failed = "EIN"
    if ftp do
      failed
    else
      mark = calculate_grade(avg_labs, avg_homework, midterm, final)
      index = ["A+", "A", "A-", "B+", "B", "C+", "C", "D+", "D", "E", "F"]
      letter_numeric(mark, index)
    end
  end

  def numeric_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(homework)
    avg_labs = avg(labs)
    avg_exams = (midterm + final) / 2
    num_labs = filter_and_count(labs, 0.25)

    ftp = failed_to_participate(avg_homework, avg_exams, num_labs)
    failed = 0
    if ftp do
      failed
    else
      index = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
      mark = calculate_grade(avg_labs, avg_homework, midterm, final)
      letter_numeric(mark, index)
    end
  end
end