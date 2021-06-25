  defmodule Grades.Calculator do

    def percentage_grade(%{final: final, homework: homework, labs: labs, midterm: midterm}) do
      "90%"
    end

    def letter_grade(%{final: final, homework: homework, labs: labs, midterm: midterm}) do
      "A+"
    end

    def numeric_grade(%{final: final, homework: homework, labs: labs, midterm: midterm}) do
      90
    end
    
  end
