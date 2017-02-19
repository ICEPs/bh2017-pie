class CreateTransactions < ActiveRecord::Migration[5.0]
  def change
    create_table :transactions do |t|
      t.references :item, foreign_key: true
      t.references :donation, foreign_key: true
      t.references :benefactor, foreign_key: {to_table: :users}
      t.references :beneficiary, foreign_key: {to_table: :users}

      t.timestamps
    end
  end
end
