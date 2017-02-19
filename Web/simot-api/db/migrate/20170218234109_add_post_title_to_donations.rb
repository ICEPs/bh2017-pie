class AddPostTitleToDonations < ActiveRecord::Migration[5.0]
  def change
    change_table :donations do |t|
      t.string :donation_title
    end
  end
end
